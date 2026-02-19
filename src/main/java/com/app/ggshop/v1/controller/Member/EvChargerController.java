package com.app.ggshop.v1.controller.Member;

//import ch.qos.logback.core.model.Model;
import com.app.ggshop.v1.domain.EvChargerVO;
import com.app.ggshop.v1.dto.EvChargerDTO;
import com.app.ggshop.v1.dto.MemberDTO;
import com.app.ggshop.v1.dto.PostWithPagingDTO;
import com.app.ggshop.v1.service.EvChargerService;
import com.app.ggshop.v1.service.Login.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping("/ev")
@RequiredArgsConstructor
@Slf4j
public class EvChargerController {

    private final EvChargerService evChargerService;
    private final MemberService memberService;


    // 목록
    @GetMapping("/evcharger/listorigin")
    public String getEvChargerList(Model model) {
        Long companyId = 1L; // 예시, 실제 로그인한 회사 ID로 대체 가능
        // 서비스에서 리스트 받아오기
        List<EvChargerVO> chargerList = evChargerService.getEvChargerList(companyId);
        // 모델에 리스트 담기
        model.addAttribute("chargerList", chargerList);
        return "/company-admin/company-charger"; // Thymeleaf 뷰 이름
    }

    // 등록 페이지
    @GetMapping("/evcharger")
    public String goToEvChargerForm() {
        return "/company-admin/company-charger-create";
    }

    // 등록 처리
    @PostMapping("/evcharger")
    public String registerEvCharger(EvChargerDTO evChargerDTO) {
        log.info("▶ 충전소 등록 요청 DTO: {}", evChargerDTO);
        evChargerService.registerEvCharger(evChargerDTO);

        return "redirect:/ev/evcharger/list";
    }



    @GetMapping("/evcharger/list/{page}")
    public String getEvChargerListWithPaging(@PathVariable int page, Model model) {
        PostWithPagingDTO result = evChargerService.list(page);

        // 데이터 확인용 로그 추가!
        log.info("=== 충전소 데이터 확인 ===");
        result.getEvChargerList().forEach(charger -> {
            log.info("ID: {}", charger.getId());
            log.info("주소(installAddress): {}", charger.getInstallAddress());
            log.info("번호(stationNumber): {}", charger.getStationNumber());
            log.info("내용(chargerNote): {}", charger.getChargerNote());
            log.info("날짜(installDate): {}", charger.getInstallDate());
            log.info("---");
        });

        model.addAttribute("chargerList", result.getEvChargerList());
        model.addAttribute("pagination", result.getCriteria());

        log.info("페이지: {}, 데이터 개수: {}", page, result.getEvChargerList().size());

        return "/company-admin/company-charger";
    }

    @GetMapping("/evcharger/list")  // ← 이것도 수정
    public String redirectToFirstPage() {
        return "redirect:/ev/evcharger/list/1";
    }


    // 검색 기능
    @GetMapping("/evcharger/search")
    public String searchEvChargers(
            @RequestParam(defaultValue = "all") String searchType,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            Model model) {

        log.info("검색 - 타입: {}, 키워드: {}, 페이지: {}", searchType, keyword, page);

        // 검색 결과 조회
        PostWithPagingDTO result = evChargerService.searchEvChargers(searchType, keyword, page);

        // 모델에 담기
        model.addAttribute("chargerList", result.getEvChargerList());
        model.addAttribute("pagination", result.getCriteria());
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        log.info("검색 결과: {} 건", result.getEvChargerList().size());

        return "/company-admin/company-charger";
    }

    // 상세 페이지 (읽기 전용)
    @GetMapping("/evcharger/detail/{id}")
    public String getEvChargerDetail(@PathVariable Long id, Model model) {
        EvChargerDTO charger = evChargerService.getEvChargerById(id);
        model.addAttribute("charger", charger);
        log.info("상세 페이지 - 충전소 ID: {}, 데이터: {}", id, charger);
        return "/company-admin/company-charger-update";
    }

    // 수정 페이지 이동
    @GetMapping("/evcharger/update/{id}")
    public String getEvChargerUpdateForm(@PathVariable Long id, Model model) {
        EvChargerDTO charger = evChargerService.getEvChargerById(id);
        model.addAttribute("charger", charger);
        return "/company-admin/company-charger-updateLv2";
    }

    // 수정 처리
    @PostMapping("/evcharger/update/{id}")
    public String updateEvCharger(@PathVariable Long id, EvChargerDTO evChargerDTO) {
        evChargerDTO.setId(id);
        evChargerService.updateEvCharger(evChargerDTO);
        return "redirect:/ev/evcharger/list/1";
    }



    /**
     * 충전기 삭제
     */
    @PostMapping("/evcharger/delete/{id}")
    public RedirectView deleteCharger(@PathVariable Long id) {
        log.info("▶ 충전기 삭제 요청 - ID: {}", id);

        evChargerService.remove(id);  // 또는 deleteCharger(id)

        log.info("▶ 충전기 삭제 완료 - ID: {}", id);

        return new RedirectView("/ev/evcharger/list/1");
    }


    @GetMapping("/login/test")
    public String login_test() {

        return "/company-admin/top";
        //        return "/main";
//        return "includes/footers/company-footer";
//        return "includes/headers/company-header";
//        return "/paymentHistory/paymentdetailed";
//        return "/paymentHistory/paymentHistroy";
//        return "/modals/modal_toast-common";
//        return "/modals/modal_form-pwChange";
//        return "/modals/modal_bottomsheet-zzone";  // V2G 한전 에 전기를 팔수 있는 모달창
//        return "/modals/modal_alert-pwChange";
//        return "/modals/modal_alert-alarm";
//        return "/modals/modal_alert-zzone"; // V2G  한전 에 전기를 팔수 있는 모달창
//        return "/company-admin/company-charger-detail";
//        return "/company-admin/company-sidetap";
//        return "/company-admin/company-search-button";
//        return "/company-admin/company-list";
//        return "/company-admin/company-employee-update";
//        return "/login/login_mobile_check";
//        return "/p2p/p2p_list";
    }

    @GetMapping("/main")
    public String chargerMain () {

        return "/main";
    }

    @GetMapping("/p2p/list")
    public String p2p() {

        return "/p2p/p2p_list";
    }



    @GetMapping("/evcharger/detail")
    public String companyDetail() {
        return "/company-admin/company-charger-detail";

    }

    @GetMapping("/evcharger/update")
    public String companyUpdate() {
        return "/company-admin/company-charger-update";
    }

    @GetMapping("/company/list")
    public String companylist() {
        return "/company-admin/company-list";
//        return "/company-admin/company-search-button";
//        return "/company-admin/company-list";
//        return "/company-admin/company-employee-update";
//        return "/login/login_mobile_check";
//        return "/p2p/p2p_list";
    }



    @GetMapping("/company/emp/update")
    public String companyEmpUpdate() {
        return "/company-admin/company-employee-update";
//        return "/company-admin/company-search-button";
//        return "/company-admin/company-list";
//        return "/company-admin/company-employee-update";
    }

    @GetMapping("/company/company/search")
    public String companySearch() {
        return "/company-admin/company-search-button";

    }

    @GetMapping("/company/sidetap")
    public String companySideTap() {
        return "/company-admin/company-sidetap";

    }






}
