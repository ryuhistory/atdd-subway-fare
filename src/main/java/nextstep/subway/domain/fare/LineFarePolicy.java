package nextstep.subway.domain.fare;

import java.util.List;

import nextstep.subway.domain.Line;

public class LineFarePolicy implements AdditionalFarePolicy {

	private final List<Line> lineList;

	public LineFarePolicy(List<Line> lineList) {
		this.lineList = lineList;
	}

	@Override
	public long calculateAdditionalFee() {
		return lineList.stream()
			.mapToInt(Line::getAdditionalFee)
			.max()
			.orElse(0);
	}
}
