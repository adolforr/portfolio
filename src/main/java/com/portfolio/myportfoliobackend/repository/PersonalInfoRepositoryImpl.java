package com.portfolio.myportfoliobackend.repository;

import com.portfolio.myportfoliobackend.model.PersonalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepositoryImpl implements IPersonalInfoRepository {


    private final JdbcTemplate jdbcTemplate;

    private final RowMapper <PersonalInfo> personalInfoRowMapper = (rs, rowNum) -> {
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setId(rs.getLong("id"));
        personalInfo.setFirstName(rs.getString("first_name"));
        personalInfo.setLastName(rs.getString("last_name"));
        personalInfo.setTitle(rs.getString("title"));
        personalInfo.setProfileDescription(rs.getString("profile_description"));
        personalInfo.setProfileImageUrl(rs.getString("profile_image_url"));
        personalInfo.setYearsOfExperience(rs.getObject("years_of_experience", Integer.class)); // Usar getObject para nulos
        personalInfo.setEmail(rs.getString("email"));
        personalInfo.setPhoneNumber(rs.getString("phone"));
        personalInfo.setLinkedInUrl(rs.getString("linkedin_url"));
        personalInfo.setGithubUrl(rs.getString("github_url"));

        return personalInfo;
    };

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {

        if (personalInfo.getId() == null) {
            String sql = "insert into personal_info (first_name, last_name, title, profile_description, profile_image_url, years_of_experience, email, phone, linkedin_url, github_url) " +
                         "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {

                var ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, personalInfo.getFirstName());
                ps.setString(2, personalInfo.getLastName());
                ps.setString(3, personalInfo.getTitle());
                ps.setString(4, personalInfo.getProfileDescription());
                ps.setString(5, personalInfo.getProfileImageUrl());

                if (personalInfo.getYearsOfExperience() != null) {
                    ps.setInt(6, personalInfo.getYearsOfExperience());
                } else {
                    ps.setNull(6, java.sql.Types.INTEGER);
                }

                ps.setString(7, personalInfo.getEmail());
                ps.setString(8, personalInfo.getPhoneNumber());
                ps.setString(9, personalInfo.getLinkedInUrl());
                ps.setString(10, personalInfo.getGithubUrl());
                return ps;
            }, keyHolder);

            personalInfo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());


        } else {
            String sql = "update personal_info set first_name = ?, last_name = ?, title = ?, profile_description = ?, profile_image_url = ?, years_of_experience = ?, email = ?, phone = ?, linkedin_url = ?, github_url = ? where id = ?";

            jdbcTemplate.update(sql,
                    personalInfo.getFirstName(),
                    personalInfo.getLastName(),
                    personalInfo.getTitle(),
                    personalInfo.getProfileDescription(),
                    personalInfo.getProfileImageUrl(),
                    personalInfo.getYearsOfExperience(),
                    personalInfo.getEmail(),
                    personalInfo.getPhoneNumber(),
                    personalInfo.getLinkedInUrl(),
                    personalInfo.getGithubUrl(),
                    personalInfo.getId()
            );


        }

        return personalInfo;
    }

//    @Override
//    public Optional<PersonalInfo> findById(Long id) {
//        String sql = "select * from personal_info where id = ?";
//        List<PersonalInfo> results = jdbcTemplate.query(sql, personalInfoRowMapper, id);
//
//        return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
//    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        String sql = "select * from personal_info where id = ?";
        try{
            PersonalInfo personalInfo = jdbcTemplate.queryForObject(sql, personalInfoRowMapper, id);
            return Optional.ofNullable(personalInfo);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql = "select * from personal_info";

        return jdbcTemplate.query(sql,personalInfoRowMapper);
    }

    @Override
    public void deleteById(Long id) {

        String sql = "delete from personal_info where id = ?";

        jdbcTemplate.update(sql,id);

    }
}
