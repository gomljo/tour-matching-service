package com.backpacking.guide.guide.repository;

import com.backpacking.guide.guide.domain.model.GuideInfo;
import com.backpacking.member.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GuideInfoRepository extends JpaRepository<GuideInfo, Long> {

    Optional<GuideInfo> findById(long guideInfoId);
    Long deleteGuideInfoById(Long id);
    @Query("select g.id from GuideInfo g inner join g.member where g.member.email = :email")
    Optional<Long> findFirstByMemberEmail(@Param("email") String email);
    @Query("select g.member from GuideInfo g where g.id = :guideInfoId")
    Optional<Member> findMemberByGuideInfoId(@Param("guideInfoId") Long id);


}
