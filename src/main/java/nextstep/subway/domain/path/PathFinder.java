package nextstep.subway.domain.path;

import nextstep.subway.domain.Sections;
import nextstep.subway.domain.Station;

public interface PathFinder {
	Sections findPath(Station source, Station target);
}
