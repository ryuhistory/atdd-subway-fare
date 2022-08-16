package nextstep.subway.unit;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.subway.domain.Line;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Station;

class LineTest {

	Line line;
	Station 강남역;
	Station 역삼역;
	Station 삼성역;

	@BeforeEach
	void setUp() {
		line = new Line("2호선", "green", 0);
		강남역 = new Station("강남역");
		역삼역 = new Station("역삼역");
		삼성역 = new Station("삼성역");
	}

	@Test
	void addSection() {
		line.addSection(강남역, 역삼역, 10, 3);
		line.addSection(역삼역, 삼성역, 5, 5);

		assertThat(line.getStations()).containsExactly(강남역, 역삼역, 삼성역);
	}

	@DisplayName("상행 기준으로 목록 중간에 추가할 경우")
	@Test
	void addSectionInMiddle() {
		line.addSection(강남역, 역삼역, 10, 5);
		line.addSection(강남역, 삼성역, 5, 7);

		assertThat(line.getSections().size()).isEqualTo(2);
		Section section = line.getSections().stream()
			.filter(it -> it.getUpStation() == 강남역)
			.findFirst().orElseThrow(RuntimeException::new);
		assertThat(section.getDownStation()).isEqualTo(삼성역);
		assertThat(section.getDistance()).isEqualTo(5);
		assertThat(section.getDuration()).isEqualTo(7);

	}

	@DisplayName("하행 기준으로 목록 중간에 추가할 경우")
	@Test
	void addSectionInMiddle2() {
		line.addSection(강남역, 역삼역, 10, 8);
		line.addSection(삼성역, 역삼역, 5, 5);

		assertThat(line.getSections().size()).isEqualTo(2);
		Section section = line.getSections().stream()
			.filter(it -> it.getUpStation() == 강남역)
			.findFirst().orElseThrow(RuntimeException::new);
		assertThat(section.getDownStation()).isEqualTo(삼성역);
		assertThat(section.getDistance()).isEqualTo(5);
		assertThat(section.getDuration()).isEqualTo(3);
	}

	@DisplayName("목록 앞에 추가할 경우")
	@Test
	void addSectionInFront() {
		line.addSection(강남역, 역삼역, 10, 5);
		line.addSection(삼성역, 강남역, 5, 8);

		assertThat(line.getSections().size()).isEqualTo(2);
		Section section = line.getSections().stream()
			.filter(it -> it.getUpStation() == 강남역)
			.findFirst().orElseThrow(RuntimeException::new);
		assertThat(section.getDownStation()).isEqualTo(역삼역);
		assertThat(section.getDistance()).isEqualTo(10);
		assertThat(section.getDuration()).isEqualTo(5);
	}

	@DisplayName("목록 뒤에 추가할 경우")
	@Test
	void addSectionBehind() {
		line.addSection(강남역, 역삼역, 10, 8);
		line.addSection(역삼역, 삼성역, 5, 1);

		assertThat(line.getSections().size()).isEqualTo(2);
		Section section = line.getSections().stream()
			.filter(it -> it.getUpStation() == 역삼역)
			.findFirst().orElseThrow(RuntimeException::new);
		assertThat(section.getDownStation()).isEqualTo(삼성역);
		assertThat(section.getDistance()).isEqualTo(5);
		assertThat(section.getDuration()).isEqualTo(1);
	}

	@Test
	void getStations() {
		line.addSection(강남역, 역삼역, 10, 3);
		line.addSection(강남역, 삼성역, 5, 8);

		List<Station> result = line.getStations();

		assertThat(result).containsExactly(강남역, 삼성역, 역삼역);
	}

	@DisplayName("이미 존재하는 구간 추가 시 에러 발생")
	@Test
	void addSectionAlreadyIncluded() {
		line.addSection(강남역, 역삼역, 10, 1);

		assertThatThrownBy(() -> line.addSection(강남역, 역삼역, 5, 5))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void removeSection() {
		line.addSection(강남역, 역삼역, 10, 3);
		line.addSection(역삼역, 삼성역, 5, 1);

		line.deleteSection(삼성역);

		assertThat(line.getStations()).containsExactly(강남역, 역삼역);
	}

	@Test
	void removeSectionInFront() {
		line.addSection(강남역, 역삼역, 10, 4);
		line.addSection(역삼역, 삼성역, 5, 6);

		line.deleteSection(강남역);

		assertThat(line.getStations()).containsExactly(역삼역, 삼성역);
	}

	@Test
	void removeSectionInMiddle() {
		line.addSection(강남역, 역삼역, 10, 1);
		line.addSection(역삼역, 삼성역, 5, 2);

		line.deleteSection(역삼역);

		assertThat(line.getStations()).containsExactly(강남역, 삼성역);
	}

	@DisplayName("구간이 하나인 노선에서 역 삭제 시 에러 발생")
	@Test
	void removeSectionNotEndOfList() {
		line.addSection(강남역, 역삼역, 10, 2);

		assertThatThrownBy(() -> line.deleteSection(역삼역))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
