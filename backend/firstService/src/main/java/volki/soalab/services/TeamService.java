package volki.soalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volki.soalab.dto.hunter.HunterDtoWithId;
import volki.soalab.dto.hunter.HunterDtoWithIdList;
import volki.soalab.dto.team.TeamDto;
import volki.soalab.dto.team.TeamDtoWithId;
import volki.soalab.dto.team.TeamDtoWithIdAndHunters;
import volki.soalab.dto.team.TeamDtoWithIdList;
import volki.soalab.dtoManipulator.DtoListManipulator;
import volki.soalab.entities.hunter.HunterEntity;
import volki.soalab.entities.team.TeamEntity;
import volki.soalab.repositories.TeamRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final DtoListManipulator dtoListManipulator;

    @Autowired
    public TeamService(TeamRepository teamRepository, DtoListManipulator dtoListManipulator) {
        this.teamRepository = teamRepository;
        this.dtoListManipulator = dtoListManipulator;
    }

    public TeamDtoWithIdList getTeams(List<String> filter, List<String> sort, Long page, Long pageSize) {
        List<TeamDtoWithId> teamDtoWithIdList = ((List<TeamEntity>) teamRepository.findAll()).stream()
                .map(TeamDtoWithId::new)
                .toList();

        if (filter != null && !filter.isEmpty()) {
            teamDtoWithIdList = dtoListManipulator.filterTeams(teamDtoWithIdList, filter);
        }

        if (sort != null && !sort.isEmpty()) {
            teamDtoWithIdList = dtoListManipulator.sortTeam(teamDtoWithIdList, sort);
        }

        if (page != null && pageSize != null) {
            teamDtoWithIdList = dtoListManipulator.page(teamDtoWithIdList, page, pageSize);
        }

        return new TeamDtoWithIdList(teamDtoWithIdList);
    }

    public ResponseEntity<TeamDtoWithIdAndHunters> getTeamById(long id) {
        Optional<TeamEntity> optionalTeamEntity = teamRepository.findById(id);
        if (optionalTeamEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TeamDtoWithIdAndHunters teamDtoWithIdAndHunters = new TeamDtoWithIdAndHunters(optionalTeamEntity.get());
        return ResponseEntity.ok(teamDtoWithIdAndHunters);
    }

    public ResponseEntity<TeamDtoWithId> addTeam(TeamDto teamDto) {
        TeamEntity teamEntity = teamRepository.save(new TeamEntity(teamDto));
        return ResponseEntity.ok(new TeamDtoWithId(teamEntity));
    }
}
