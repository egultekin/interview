package cci;

class XMLEncoding {
	static class Element {
		String tag;
		Map<String, String> attributes;
		List<Element> children;
		Map<Integer, String> tagMapping;
		
		public String encode() {
			
		}
	}
	
	int index;
	char[] xml;
	
	public XMLEncoding(char[] input) {
		xml = input;
		index = 0;
	}
	
	public static Element parseElement() {
		int start = index, end = index;
		boolean tagFound = false, tokenFound = false;
		
	}
}
}