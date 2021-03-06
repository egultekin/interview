package cci;

import java.util.*;

class XMLEncoding {
	static class Element {
		String tag;
		Map<String, String> attributes;
		List<Element> children;
		Map<Integer, String> tagMapping;
		int tagKey;
		final char END = '0';
		final char DELIMETER = ' ';
		
		public Element() {
			tag = null;
			attributes = new HashMap<String, String>();
			children = new Vector<Element>();
			tagMapping = new HashMap<Integer, String>();
		}

		public String print() {
			StringBuilder builder = new StringBuilder();
			builder.append("Tag: ").append(tag).append("\n");
			if (attributes.size() == 0) builder.append("No attributes\n");
			else {
				builder.append("Attributes: ");
				Iterator<Map.Entry<String, String>> it = attributes.entrySet().iterator();
				Map.Entry<String, String> next = it.next();
				builder.append(next.getKey()).append(':').append(next.getValue());
				while (it.hasNext()) {
					next = it.next();
					builder.append(',').append(next.getKey()).append(':').append(next.getValue());
				}
				builder.append('\n');
			}
			if (children.size() == 0) builder.append("No children\n");
			else {
				builder.append("Children: ");
				for (Element child : children) builder.append(child.print());
			}
			return builder.toString();
		}
		
		public String encode(int firstKey) {
			StringBuilder result = new StringBuilder();
			if (attributes.size() == 0 && children.size() == 0) result.append(tag);
			else {
				tagMapping.put(firstKey, tag);
				result.append(firstKey++);

				Iterator<Map.Entry<String, String>> attIt = attributes.entrySet().iterator();
				while (attIt.hasNext()) {
					result.append(DELIMETER);
					Map.Entry<String, String> entry = attIt.next();
					tagMapping.put(firstKey, entry.getKey());
					result.append(firstKey++).append(DELIMETER).append(entry.getValue());
				}
				result.append(DELIMETER).append(END);
				Iterator<Element> it = children.iterator();
				while (it.hasNext()) {
					result.append(DELIMETER);
					Element next = it.next();
					String childStr = next.encode(firstKey);
					result.append(childStr);
					Iterator<Map.Entry<Integer, String>> tagIt = next.tagMapping.entrySet().iterator();
					while (tagIt.hasNext()) {
						Map.Entry<Integer, String> e = tagIt.next();
						tagMapping.put(e.getKey(), e.getValue());
					}
				}
				result.append(DELIMETER).append(END);
			}

			return result.toString();
		}
	}

	int i;
	char[] xml;

	public XMLEncoding(char[] input) {
		xml = input;
		i = 0;
	}

	public String nextToken() {
		int start = i;
		boolean foundToken = false;
		while (i < xml.length && (!foundToken || xml[i] != '>') && (!foundToken || xml[i] != ' ') && (!foundToken || xml[i] != '<')) {
			if (xml[i] != '<' && xml[i] != '>' && xml[i] != ' ' && xml[i] != '\r' && xml[i] != '\n' && !foundToken) {
				start = i;
				foundToken = true;
			}
			i++;
		}

		String result = String.valueOf(Arrays.copyOfRange(xml, start, i));
		//if (xml[i] != '<') i++;
		return result;
	}

//	public Element parseElement(String parsed, String parent) {
//		Element tagElement = new Element();
//		String token = (null != parsed) ? parsed : nextToken();
//		String tagEndToken = (null != parent) ? '/' + parent : '/' + token;
//		String tag = token;
//		while (i < xml.length && !token.equals(tagEndToken)) {
//			token = nextToken();
//			//if (null != parent) tag = tag + " " + token;
//			String[] attributePair = null;
//			while (i < xml.length && !token.equals(tagEndToken) && (attributePair = parseAttribute(token)) != null) {
//				tagElement.attributes.put(attributePair[0], attributePair[1]);
//				if (i < xml.length) token = nextToken();
//			}
//			while (i < xml.length && !token.equals(tagEndToken)) {
//				Element child = parseElement(token, tag);
//				tagElement.children.add(child);
//				if (i < xml.length) token = nextToken();
//			}
//		}
//		tagElement.tag = tag;
//		return tagElement;
//	}
	
	public Element parse() {
		Element topMost = null;
		if ((topMost = parseTag()) == null) throw new RuntimeException("Invalid input detected.");
		for (Element child : parseChildren(topMost.tag)) 
			topMost.children.add(child);
		return topMost;
	}
	
	public Element parseTag() {
		if (xml[i] != '<') return null;
		Element element = new Element();
		element.tag = nextToken();
		while (i < xml.length && xml[i] != '>') {
			Map<String, String> am = parseAttributes();
			if (null != am) {
				Iterator<Map.Entry<String, String>> it = am.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> ent = it.next();
					element.attributes.put(ent.getKey(), ent.getValue());
				}
			}
		}
		i++;
		return element;
	}
	
	public List<Element> parseChildren(String tag) {
		String tagEndToken = '/' + tag;
		List<Element> res = new Vector<Element>();
		Element child = null;
		while ((child = parseTag()) != null && !child.tag.equals(tagEndToken)) {
			for (Element grandChild : parseChildren(child.tag)) child.children.add(grandChild);
			res.add(child);
		}
		
		if (null == child) {
			
			String token = nextToken();
			String value = "";
			while (i < xml.length && !token.equals(tagEndToken)) {
				if (value.equals("")) value = token;
				else value = value + " " + token;
				token = nextToken();
			}
			
			if (!value.equals("")) {
				child = new Element();
				child.tag = value;
				res.add(child);
			}
		}
		
		return res;
	}

	public Map<String, String> parseAttributes() {
		if (xml[i] == '>') return null;
		Map<String, String> am = new HashMap<String, String>();
		while (i < xml.length && xml[i] != '>' && xml[i] != '<') {
			String attributeToken = nextToken();
			String[] result = attributeToken.split("=");
			String value = result[1];
			if (value.startsWith("\"") && value.endsWith("\"")) result[1] = value.substring(1, value.length()-1);
			am.put(result[0], value);
		}
		return am;
	}
	
	public static void main(String[] args) {
//		String xml = "<emre age=\"38\">" +
//		"<umut>childofumut</umut>" +
//				"</emre>";
		String xml = "<family lastName=\"McDowell\" state=\"CA\">" +
		"<person firstName=\"Gayle\">Some Message</person>" +
		"</family>";
		XMLEncoding xmlEnc = new XMLEncoding(xml.toCharArray());
		//Element elem = xmlEnc.parseElement(null, null);
		Element elem = xmlEnc.parse();
		System.out.println(elem.encode(1));
		for (Map.Entry<Integer, String> ent : elem.tagMapping.entrySet()) {
			System.out.printf("%d: %s\n", ent.getKey(), ent.getValue());
		}
	}
}
