package nextstep.subway.domain;

import java.util.List;

import nextstep.subway.domain.fee.DistanceFeePolicy;
import nextstep.subway.domain.fee.FeePolicy;

public class Path {
	private Sections sections;
	private FeePolicy feePolicy;

	public Path(Sections sections) {
		this.sections = sections;
		this.feePolicy = new DistanceFeePolicy(this.extractDistance());
	}

	public Sections getSections() {
		return sections;
	}

	public int extractDistance() {
		return sections.totalDistance();
	}

	public int extractDuration() {
		return sections.totalDuration();
	}

	public List<Station> getStations() {
		return sections.getStations();
	}

	public long getFee() {
		return this.feePolicy.calculateFee();
	}
}
