package store.mybooks.resource.point_rule.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import store.mybooks.resource.error.Utils;
import store.mybooks.resource.point_rule.dto.request.PointRuleCreateRequest;
import store.mybooks.resource.point_rule.dto.request.PointRuleModifyRequest;
import store.mybooks.resource.point_rule.dto.response.PointRuleCreateResponse;
import store.mybooks.resource.point_rule.dto.response.PointRuleModifyResponse;
import store.mybooks.resource.point_rule.dto.response.PointRuleResponse;
import store.mybooks.resource.point_rule.service.PointRuleService;

/**
 * packageName    : store.mybooks.resource.point_rule.controller<br>
 * fileName       : PointRuleController<br>
 * author         : minsu11<br>
 * date           : 3/7/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/7/24        minsu11       최초 생성<br>
 */

@RestController
@RequestMapping("/api/point-rules")
@RequiredArgsConstructor
public class PointRuleController {
    private final PointRuleService pointRuleService;

    /**
     * methodName : getPointRuleResponse<br>
     * author : minsu11<br>
     * description : {@code id}에 맞는 포인트 규정 조회.
     * <br> *
     *
     * @param id 조회할 {@code id}
     * @return response entity
     */
    @GetMapping("{id}")
    public ResponseEntity<PointRuleResponse> getPointRuleResponse(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pointRuleService.getPointRuleResponse(id));
    }

    /**
     * methodName : getPointRuleResponseList<br>
     * author : minsu11<br>
     * description : 전체 포인트 규정 조회.
     * <br> *
     *
     * @return response entity
     */
    @GetMapping
    public ResponseEntity<List<PointRuleResponse>> getPointRuleResponseList() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pointRuleService.getPointRuleList());
    }

    /**
     * methodName : createResponseResponse<br>
     * author : minsu11<br>
     * description : 포인트 규정 등록.
     * <br> *
     *
     * @param request       등록할 포인트 규정
     * @param bindingResult 유효성 검사
     * @return response entity
     */
    @PostMapping
    public ResponseEntity<PointRuleCreateResponse> createPointRuleResponse(@RequestBody PointRuleCreateRequest request,
                                                                           BindingResult bindingResult) {
        Utils.validateRequest(bindingResult);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pointRuleService.createPointRuleResponse(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PointRuleModifyResponse> modifyPointRuleResponse(@RequestBody PointRuleModifyRequest request,
                                                                           @PathVariable Integer id,
                                                                           BindingResult bindingResult) {
        Utils.validateRequest(bindingResult);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pointRuleService.modifyPointRuleResponse(request, id));
    }
}
