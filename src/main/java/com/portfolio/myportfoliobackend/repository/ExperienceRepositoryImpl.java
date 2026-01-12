package com.portfolio.myportfoliobackend.repository;

import com.portfolio.myportfoliobackend.model.Experience;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExperienceRepositoryImpl implements IExperienceRepository{

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Experience> experienceRowMapper = (rs, rowNum) -> {
        Experience experience = new Experience();
        experience.setId(rs.getLong("id"));
        experience.setJobTitle(rs.getString("job_title"));
        experience.setCompanyName(rs.getString("company_name"));
        experience.setStartDate(rs.getObject("start_date", LocalDate.class));
        experience.setEndDate(rs.getObject("end_date", LocalDate.class));
        experience.setDescription(rs.getString("description"));
        experience.setPersonalInfoId(rs.getLong("personal_info_id"));
        return experience;
    };

    @Override
    public List<Experience> findAll() {
        String sql = "select * from experience order by id";
        return jdbcTemplate.query(sql, experienceRowMapper);
    }

    @Override
    public Optional<Experience> findById(Long id) {
        String sql = "select * from experience where id = ?";
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, experienceRowMapper, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Experience save(Experience experience) {
        if (experience.getId() == null) {
            String sql = "INSERT INTO experience (job_title, company_name, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                var ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, experience.getJobTitle());
                ps.setString(2, experience.getCompanyName());
                ps.setObject(3, experience.getStartDate());
                ps.setObject(4, experience.getEndDate());
                ps.setString(5, experience.getDescription());
                ps.setLong(6, experience.getPersonalInfoId());
                return ps;
            }, keyHolder);
            experience.setId(keyHolder.getKey().longValue());
        } else {
            String sql = "UPDATE experience SET job_title = ?, company_name = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, experience.getJobTitle(), experience.getCompanyName(), experience.getStartDate(), experience.getEndDate(), experience.getDescription(), experience.getPersonalInfoId(), experience.getId());
        }
        return experience;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM experience WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public List<Experience> findByPersonalInfoId(Long personalInfoId) {
        String sql = "select * from experience where personal_info_id = ?";
        return jdbcTemplate.query(sql, experienceRowMapper, personalInfoId);
    }
}
