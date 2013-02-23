package trees;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import common.Pair;

/**
 * Write functions to serialize/deserialize an N-ary tree containing only
 * strings to/from a byte stream.
 * 
 * The serialization format is the following:
 * 
 * |NUMBER OF NODES|    NODE METADATA    |  STRINGS  |
 *       (int32)       (int32, int32)[]     string[]
 */
public class NaryTreeSerialization {

	public static void serialize(NaryTreeNode<String> root, String filePath)
			throws IOException {
		if (filePath == null) {
			throw new NullPointerException("File path cannot be null.");
		}

		BufferedOutputStream fileHandle = new BufferedOutputStream(
				new FileOutputStream(filePath));
		try {
			if (root == null) {
				serializeMetadataSize(fileHandle, 0);
				return;
			}

			int numNodes = 0;
			List<String> strings = new ArrayList<String>();
			// Metadata is a pair containing string length and number of
			// children.
			List<Pair<Integer, Integer>> metadata = new ArrayList<Pair<Integer, Integer>>();

			// Doing BFS below to traverse the tree.
			Queue<NaryTreeNode<String>> queue = new LinkedList<NaryTreeNode<String>>();
			queue.add(root);
			while (!queue.isEmpty()) {
				NaryTreeNode<String> current = queue.remove();
				List<NaryTreeNode<String>> children = current.getChildren();

				for (NaryTreeNode<String> child : children) {
					if (child == null) {
						throw new IllegalArgumentException(
								"Your n-ary tree is fucked up with nulls all over the place.");
					}

					queue.add(child);
				}

				Pair<Integer, Integer> currentMetadata = new Pair<Integer, Integer>();
				String str = current.getValue();
				if (str == null) {
					currentMetadata.setFirst(-1);
				} else {
					currentMetadata.setFirst(str.length());
					strings.add(str);
				}

				currentMetadata.setSecond(children.size());
				metadata.add(currentMetadata);
				strings.add(str);
				numNodes++;

				if (numNodes < 0) {
					throw new IllegalArgumentException(
							"Number of nodes cannot exceed size of maximum integer.");
				}
			}

			serializeMetadataSize(fileHandle, numNodes);
			serializeMetadata(fileHandle, metadata);
			serializeStrings(fileHandle, strings);

		} finally {
			fileHandle.close();
		}
	}

	private static NaryTreeNode<String> deserialize(String filePath)
			throws IOException {
		if (filePath == null) {
			throw new NullPointerException("File path cannot be null.");
		}

		NaryTreeNode<String> root = null;
		BufferedInputStream fileHandle = new BufferedInputStream(
				new FileInputStream(filePath));
		try {
			int metadataSize = deserializeMetadataSize(fileHandle);
			if (metadataSize == 0) {
				return root;
			}

			List<Pair<Integer, Integer>> metadata = deserializeMetadata(
					fileHandle, metadataSize);
			root = new NaryTreeNode<String>();

			// Doing BFS below to traverse the tree.
			Queue<NaryTreeNode<String>> queue = new LinkedList<NaryTreeNode<String>>();
			Iterator<Pair<Integer, Integer>> iter = metadata.iterator();
			while (!queue.isEmpty()) {
				Pair<Integer, Integer> currentMetadata = iter.next();
				NaryTreeNode<String> current = queue.remove();

				int strLength = currentMetadata.getFirst();
				if (strLength != -1) {
					String str = deserializeString(fileHandle, strLength);
					current.setValue(str);
				}

				int numChildren = currentMetadata.getSecond();
				for (int count = 0; count < numChildren; count++) {
					queue.add(new NaryTreeNode<String>());
				}

				iter.next();
			}

		} finally {
			fileHandle.close();
		}

		return root;
	}

	private static void serializeMetadataSize(BufferedOutputStream fileHandle,
			int metadataSize) throws IOException {
		serializeInteger(fileHandle, metadataSize);
	}

	private static int deserializeMetadataSize(BufferedInputStream fileHandle)
			throws IOException {
		return deserializeInteger(fileHandle);
	}

	private static void serializeMetadata(BufferedOutputStream fileHandle,
			List<Pair<Integer, Integer>> metadata) throws IOException {
		for (Pair<Integer, Integer> nodeInfo : metadata) {
			serializeInteger(fileHandle, nodeInfo.getFirst());
			serializeInteger(fileHandle, nodeInfo.getSecond());
		}
	}

	private static List<Pair<Integer, Integer>> deserializeMetadata(
			BufferedInputStream fileHandle, int metadataSize)
			throws IOException {
		ArrayList<Pair<Integer, Integer>> metadata = new ArrayList<Pair<Integer, Integer>>();

		for (int count = 0; count < metadataSize; count++) {
			Pair<Integer, Integer> pair = new Pair<Integer, Integer>();
			pair.setFirst(deserializeInteger(fileHandle));
			pair.setSecond(deserializeInteger(fileHandle));
			metadata.add(pair);
		}

		return metadata;
	}

	private static void serializeStrings(BufferedOutputStream fileHandle,
			List<String> strings) throws IOException {
		for (String s : strings) {
			serializeString(fileHandle, s);
		}
	}

	private static void serializeInteger(BufferedOutputStream fileHandle,
			int value) throws IOException {
		for (int count = 0; count < 4; count++) {
			fileHandle.write((byte) value & 0xFF);
			value >>>= 8;
		}
	}

	private static int deserializeInteger(BufferedInputStream fileHandle)
			throws IOException {
		int value = 0;
		for (int count = 0; count < 4; count++) {
			value |= (long) ((fileHandle.read() & 0xFF) << (1 << count));
		}

		return value;
	}

	private static void serializeString(BufferedOutputStream fileHandle,
			String str) throws IOException {
		if (str == null) {
			throw new NullPointerException("Cannot serialize a null string.");
		}

		for (int index = 0; index < str.length(); index++) {
			serializeChar(fileHandle, str.charAt(index));
		}
	}

	private static String deserializeString(BufferedInputStream fileHandle,
			int length) throws IOException {
		if (length < 0) {
			throw new IllegalArgumentException(String.format(
					"Length should be greater than zero. You passed: %d.",
					length));
		}

		StringBuffer buffer = new StringBuffer();
		for (int index = 0; index < buffer.length(); index++) {
			buffer.append(deserializeChar(fileHandle));
		}

		return buffer.toString();
	}

	private static void serializeChar(BufferedOutputStream fileHandle, char c)
			throws IOException {
		fileHandle.write((byte) (c & 0xFF));

		c >>>= 8;
		fileHandle.write((byte) (c & 0xFF));
	}

	private static char deserializeChar(BufferedInputStream fileHandle)
			throws IOException {
		char c = (char) 0;
		c |= (char) (fileHandle.read() & 0xFF);
		c |= (char) ((fileHandle.read() & 0xFF) << 8);

		return c;
	}
}
