package nextstep.subway.domain;

import java.util.List;

import nextstep.subway.domain.fare.DistanceFarePolicy;
import nextstep.subway.domain.fare.FarePolicy;

public class Path {
	private Sections sections;
	private FarePolicy farePolicy;

	public Path(Sections sections) {
		this.sections = sections;
		this.farePolicy = new DistanceFarePolicy(this.extractDistance());
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
		return this.farePolicy.calculateFee();
	}
}
