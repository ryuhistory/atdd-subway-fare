package nextstep.subway.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.subway.domain.fare.AdditionalFarePolicy;
import nextstep.subway.domain.fare.ChildrenFeePolicy;
import nextstep.subway.domain.fare.DistanceFarePolicy;
import nextstep.subway.domain.fare.FarePolicy;
import nextstep.subway.domain.fare.TeenagerFarePolicy;

public class Path {
	private Sections sections;
	private FarePolicy farePolicy;
	private List<AdditionalFarePolicy> additionalFarePolicyList = new ArrayList<>();

	public Path(Sections sections, boolean isTeenager, boolean isChildren) {
		this.sections = sections;
		this.farePolicy = new DistanceFarePolicy(this.extractDistance());
/*
		additionalFarePolicyList.add(new LineFarePolicy(sections.getSections()
			.stream()
			.map(Section::getLine)
			.distinct()
			.collect(Collectors.toList())));

 */
		additionalFarePolicyList.add(new TeenagerFarePolicy(isTeenager, getBasicFare()));
		additionalFarePolicyList.add(new ChildrenFeePolicy(isChildren, getBasicFare()));
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

	public long getBasicFare() {
		return this.farePolicy.calculateFee();
	}

	public long getFare() {
		return this.additionalFarePolicyList
			.stream()
			.mapToLong(fare -> (int)fare.calculateAdditionalFee())
			.sum()
			+ getBasicFare();
	}

}
