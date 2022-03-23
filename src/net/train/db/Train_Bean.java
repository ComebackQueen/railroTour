package net.train.db;

public class Train_Bean {
	String NodeName, NodeId;

	public Train_Bean(String nodename, String nodeid) {
		NodeName=nodename;
		NodeId=nodeid;
	}
	public String getNodeName() {
		return NodeName;
	}

	public void setNodeName(String nodeName) {
		NodeName = nodeName;
	}

	public String getNodeId() {
		return NodeId;
	}

	public void setNodeId(String nodeId) {
		NodeId = nodeId;
	}
	
}
