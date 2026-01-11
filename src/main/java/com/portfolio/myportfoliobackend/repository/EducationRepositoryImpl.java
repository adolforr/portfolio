package com.portfolio.myportfoliobackend.repository;

import com.portfolio.myportfoliobackend.model.Education;
import com.portfolio.myportfoliobackend.model.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EducationRepositoryImpl implements IEducationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Education> educationRowMapper = (rs, rowNum) -> {
        Education education = new Education();
        education.setId(rs.getLong("id"));
        education.setDegree(rs.getString("degree"));
        education.setInstitution(rs.getString("institution"));
        education.setStartDate(rs.getObject("start_date", LocalDate.class));
        education.setEndDate(rs.getObject("end_date", LocalDate.class));
        education.setDescription(rs.getString("description"));
        education.setPersonalInfoId(rs.getString("personal_info_id"));
        return education;
    };

    @Override
    public Education save(Education education) {
        if (education.getId() == null) {
            String sql = "INSERT INTO education (degree, institution, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(conection -> {
                var ps = conection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, education.getDegree());
                ps.setString(2, education.getInstitution());
                ps.setObject(3, education.getStartDate());
                ps.setObject(4, education.getEndDate());
                ps.setString(5, education.getDescription());
                ps.setString(6, education.getPersonalInfoId());
                return ps;
            }, keyHolder);
            education.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE education SET degree = ?, institution = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, education.getDegree(), education.getInstitution(), education.getStartDate(), education.getEndDate(), education.getDescription(), education.getPersonalInfoId(), education.getId());
        }
        return education;
    }

    @Override
    public Optional<Education> findById(Long id) {
        String sql = "select * from education where id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, educationRowMapper, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Education> findAll() {
        String sql = "select * from education";
        return jdbcTemplate.query(sql, educationRowMapper);
    }

    @Override
    public void deleteById(Long id) {
      String sql = "delete from education where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Education> findAllByPersonalInfoId(Long personalInfoId) {
        String sql = "select * from education where personal_info_id = ?";
        return jdbcTemplate.query(sql, educationRowMapper, personalInfoId);
    }



}
