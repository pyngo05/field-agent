package learn.field_agent.data;

import learn.field_agent.data.mappers.AliasMapper;
import learn.field_agent.data.mappers.SecurityClearanceMapper;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AliasJdbcTemplateRepository implements AliasRepository {

    private final JdbcTemplate jdbcTemplate;

    public AliasJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Alias> findAliasesById(int agentId) {
        final String sql = "select alias.alias_id, alias.name, alias.persona, alias.agent_id, agent.first_name, agent.last_name "
                + "from alias "
                + "inner join agent on alias.agent_id = agent.agent_id "
                + "where alias.agent_id = ?;";

        return new ArrayList<>(jdbcTemplate.query(sql, new AliasMapper(), agentId));
    }

    @Override
    public List<Alias> findAll() {
        final String sql = "select alias_id, name, persona, agent_id "
                + "from alias;";
        return jdbcTemplate.query(sql, new AliasMapper());
    }

    @Override
    public Alias add(Alias alias) {

        final String sql = "insert into alias (name, persona, agent_id)"
                + "values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alias.getName());
            ps.setString(2, alias.getPersona());
            ps.setInt(3, alias.getAgentId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        alias.setAliasId(keyHolder.getKey().intValue());
        return alias;
    }

    @Override
    public boolean update(Alias alias) {

        final String sql = "update alias set "
                + "name = ?, "
                + "persona = ?, "
                + "agent_id = ?;";

        return jdbcTemplate.update(sql,
                alias.getName(),
                alias.getPersona(),
                alias.getAgentId()) > 0;
    }

    @Override
    public boolean deleteById(int aliasId) {
        return jdbcTemplate.update(
                "delete from alias where alias_id = ?", aliasId) > 0;
    }

}