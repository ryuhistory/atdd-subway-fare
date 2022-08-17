package nextstep.subway.applicaion;

import java.util.List;

import org.springframework.stereotype.Service;

import nextstep.member.application.MemberService;
import nextstep.member.application.dto.MemberResponse;
import nextstep.subway.applicaion.dto.PathResponse;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.Path;
import nextstep.subway.domain.Station;
import nextstep.subway.domain.path.PathBaseCode;
import nextstep.subway.domain.path.PathFinder;

@Service
public class PathService {
	private LineService lineService;
	private StationService stationService;
	private MemberService memberService;

	public PathService(LineService lineService, StationService stationService, MemberService memberService) {
		this.lineService = lineService;
		this.stationService = stationService;
		this.memberService = memberService;
	}

	public PathResponse findPath(Long source, Long target, PathBaseCode pathBaseCode, String userEmail) {
		MemberResponse memberResponse = memberService.findMember(userEmail);
		Station upStation = stationService.findById(source);
		Station downStation = stationService.findById(target);
		List<Line> lines = lineService.findLines();
		PathFinder pathFinder = pathBaseCode.getPathFinderClass(lines);
		Path path = new Path(pathFinder.findPath(upStation, downStation)
			, memberResponse.isTeenager()
			, memberResponse.isChildren());
		return PathResponse.of(path);
	}

}
