package cci;

import java.util.*;

class XMLEncoding {
	static class Element {
		String tag;
		Map<String, String> attributes;
		List<Element> children;
		Map<Integer, String> tagMapping;
		
		public Element() {
			tag = null;
			attributes = new HashMap<String, String>();
			children = new Vector<Element>();
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
		while (i < xml.length && xml[i] != '>' && (!foundToken || xml[i] != ' ') && (!foundToken || xml[i] != '<')) {
			if (xml[i] != '<' && xml[i] != '\r' && xml[i] != '\n' && !foundToken) {
				start = i;
				foundToken = true;
			}
			i++;
		}

		if (i < xml.length) i++;
		return String.valueOf(Arrays.copyOfRange(xml, start, i-1));
	}

	public Element parseElement(String parsed, String parent) {
		Element tagElement = new Element();
		String token = (null != parsed) ? parsed : nextToken();
		String tagEndToken = (null != parent) ? '/' + parent : '/' + token;
		tagElement.tag = token;
		while (i < xml.length && !token.equals(tagEndToken)) {
			token = nextToken();
			String[] attributePair = null;
			while (i < xml.length && !token.equals(tagEndToken) && (attributePair = parseAttribute(token)) != null) {
				tagElement.attributes.put(attributePair[0], attributePair[1]);
				if (i < xml.length) token = nextToken();
			}
			while (i < xml.length && !token.equals(tagEndToken)) {
				Element child = parseElement(token, tagElement.tag);
				tagElement.children.add(child);
				if (i < xml.length) token = nextToken();
			}
		}
		return tagElement;
	}

	public String[] parseAttribute(String attributeToken) {
		String[] result = attributeToken.split("=");
		if (result.length < 2) return null;
		String value = result[1];
		if (value.startsWith("\"") && value.endsWith("\"")) result[1] = value.substring(1, value.length()-1);
		return result;
	}
	
	public static void main(String[] args) {
		String xml = "<emre age=\"38\">" +
		"<umut>childofumut</umut>" +
				"</emre>";
		XMLEncoding xmlEnc = new XMLEncoding(xml.toCharArray());
		System.out.println(xmlEnc.parseElement(null, null).print());
	}
}
