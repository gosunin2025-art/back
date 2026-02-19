package com.app.ggshop.v1.mapper;

import com.app.ggshop.v1.common.enumeration.Provider;
import com.app.ggshop.v1.domain.MemberVO;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.service.Login.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class MemberMapperTests {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    @Transactional
    public void testInsert() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("test1@gmail.com");
        memberDTO.setMemberPassword("1234");
        memberDTO.setMemberName("test");
        memberDTO.setProvider(Provider.THREETIER);

        memberMapper.insert(memberDTO);
        memberMapper.insertOauth(memberDTO.toOAuthVO());

    }

    @Test
    public void testSelectMemberForLogin(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("ck1234@gmail.com");
        memberDTO.setMemberPassword("1234");

        Optional<MemberVO> foundMember = memberMapper.selectMemberForLogin(memberDTO);
        log.info("{}.....",foundMember.isEmpty());
    }

}
