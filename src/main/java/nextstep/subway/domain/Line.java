package nextstep.subway.domain;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Line {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String color;
	private boolean isAdditionalFeeLine;

	@Embedded
	private Sections sections = new Sections();

	public Line() {
	}

	public Line(String name, String color, boolean isAdditionalFeeLine) {
		this.name = name;
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public List<Section> getSections() {
		return sections.getSections();
	}

	public void update(String name, String color, Boolean isAdditionalFeeLine) {
		if (name != null) {
			this.name = name;
		}
		if (color != null) {
			this.color = color;
		}
		if (isAdditionalFeeLine != null) {
			this.isAdditionalFeeLine = isAdditionalFeeLine;
		}
	}

	public void addSection(Station upStation, Station downStation, int distance, int duration) {
		sections.add(new Section(this, upStation, downStation, distance, duration));
	}

	public List<Station> getStations() {
		return sections.getStations();
	}

	public void deleteSection(Station station) {
		sections.delete(station);
	}
}
