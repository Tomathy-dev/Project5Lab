package project;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Huffman encoding implementation, using a binary tree.
 *
 * @author Tomas Salgueiro n 57528
 */
public class Huffman {

	/**
	 * Fills an HashMap with it's respective code for each character in the corpus.
	 *
	 * @param corpus the corpus to be encoded
	 * @return an HashMap with the codes for each character in the corpus
	 */
	public static HashMap<Character, String> getCodes(String corpus) {
		return treeFromCorpus(corpus).getHuffmanCodes();
	}

	/**
	 * String representation of the codes.
	 *
	 * @param codes the codes to be converted to string
	 * @return the codes in string format
	 */
	public static String codesToString(HashMap<Character, String> codes) {
		StringBuilder codesRepresentation = new StringBuilder();
		
		SortedMap<Character, String> orderedCodes = new TreeMap<Character, String>(codes);
		for (Object entry : orderedCodes.entrySet()) {
			codesRepresentation.append(entry);
			codesRepresentation.append(System.lineSeparator());
		}

		return codesRepresentation.toString();		
	}

	/**
	 * Encodes a message using the codes from the corpus.
	 *
	 * @param message the message to be encoded
	 * @param codes   the codes to be used to encode the message
	 * @return the encoded message
	 */
	public static String encode(String message, HashMap<Character, String> codes) {
		StringBuilder encodedMessage = new StringBuilder();

		for (int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);
			String code = codes.get(c);
			if (code == null) {
				encodedMessage.append("-");
			} else {
				encodedMessage.append(code);
			}
		}

		return encodedMessage.toString();
	}

	/**
	 * Creates a Huffman tree from a corpus.
	 * @param corpus the corpus to be used to create the Huffman tree
	 * @return the Huffman tree
	 */
	private static HuffmanTree treeFromCorpus(String corpus) {
		return new HuffmanTree(frequencyTable(corpus));
	}

	/**
	 * Creates a frequency table from a corpus.
	 * @param corpus the corpus to be used to create the frequency table
	 * @return the frequency table
	 */
	private static HashMap<Character, Integer> frequencyTable(String corpus) {
		HashMap<Character, Integer> frequencies = new HashMap<>();

		for (int i = 0; i < corpus.length(); i++) {
			char c = corpus.charAt(i);
			Integer frequency = frequencies.get(c);
			if (frequency == null) {
				frequencies.put(c, 1);
			} else {
				frequencies.put(c, frequency + 1);
			}
		}

		return frequencies;
	}

	/**
	 * Huffman tree implementation.
	 */
	private static class HuffmanTree {

		private HuffmanNode root;

		/**
		 * Creates a Huffman tree from a frequency table.
		 * @param frequencies the frequency table to be used to create the tree
		 */
		private HuffmanTree(HashMap<Character, Integer> frequencies) {
			PriorityQueue<HuffmanNode> nodes = new PriorityQueue<>();

			for (Character c : frequencies.keySet()) {
				nodes.add(new HuffmanNode(frequencies.get(c), c));
			}

			while (nodes.size() > 1) {
				HuffmanNode left = nodes.poll();
				HuffmanNode right = nodes.poll();
				nodes.add(new HuffmanNode(left.frequency + right.frequency, left, right));
			}

			root = nodes.poll();
		}

		/**
		 * Returns the codes for each character in the corpus.
		 * @return the codes for each character in the corpus
		 */
		private HashMap<Character, String> getHuffmanCodes() {
			HashMap<Character, String> codes = new HashMap<>();

			getHuffmanCodesAux(root, "", codes);

			return codes;
		}

		/**
		 * Auxiliary method for getHuffmanCodes.
		 * @param node the node to be used to create the codes
		 * @param code the code to be used to create the codes
		 * @param codes the codes to be created
		 */
		private void getHuffmanCodesAux(HuffmanNode node, String code, HashMap<Character, String> codes) {
			if (node.isLeaf()) {
				codes.put(node.c, code);
			} else {
				getHuffmanCodesAux(node.left, code + "0", codes);
				getHuffmanCodesAux(node.right, code + "1", codes);
			}
		}
	}

	/**
	 * Huffman node implementation.
	 */
	private static class HuffmanNode implements Comparable<HuffmanNode> {

		int frequency;
		char c;
		HuffmanNode left;
		HuffmanNode right;

		/**
		 * Creates a leaf.
		 * @param frequency the frequency of the node
		 * @param c the character of the node
		 */
		// creates a leaf
		private HuffmanNode(int frequency, char c) {
			this.frequency = frequency;
			this.c = c;
			// this.left not initialized; remains null
			// this.right not initialized; remains null
		}

		/**
		 * Creates an internal node.
		 * @param frequency the frequency of the node
		 * @param left the left child
		 * @param right the right child
		 */
		// creates an internal node
		private HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
			this.frequency = frequency;
			// no need to initialize this.c, because it is not used
			this.left = left;
			this.right = right;
		}

		/**
		 * Checks if the node is a leaf.
		 * @return true if the node is a leaf, false otherwise
		 */
		private boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public int compareTo(HuffmanNode node) {
			return this.frequency - node.frequency;
		}
	}
}
