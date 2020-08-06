package holiday.manager.event.sourcing;

public final class EventStreamId {

	private String streamName;
	private int streamVersion;

	public EventStreamId(String streamName) {
		this(streamName, 1);
	}

	public EventStreamId(String streamName, int streamVersion) {
		super();

		this.setStreamName(streamName);
		this.setStreamVersion(streamVersion);
	}

	public EventStreamId(String streamNameSegment1, String streamNameSegment2) {
		this(streamNameSegment1, streamNameSegment2, 1);
	}

	public EventStreamId(String streamNameSegment1, String streamNameSegment2, int streamVersion) {
		this(streamNameSegment1 + ":" + streamNameSegment2, streamVersion);
	}

	public String streamName() {
		return this.streamName;
	}

	public int streamVersion() {
		return this.streamVersion;
	}

	public EventStreamId withStreamVersion(int streamVersion) {
		return new EventStreamId(this.streamName(), streamVersion);
	}

	private void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	private void setStreamVersion(int streamVersion) {
		this.streamVersion = streamVersion;
	}

	@Override
	public String toString() {
		return "streamName: " + streamName + ", streamVersion: " + streamVersion;
	}
}
