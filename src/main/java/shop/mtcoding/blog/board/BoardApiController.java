package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final  BoardRepository boardRepository;

    @GetMapping("/api/boards")
    public ApiUtil<List<Board>> findAll(HttpServletResponse response) {
        // response.setStatus(200);

        // 오브젝트를 리턴하면 스프링이 자동으로 json으로 받아줌
        List<Board> boardList = boardRepository.selectAll();

        // 메시지 컨버터라는 메서드가 오브젝트를 리턴할 때 자동으로 발동되어서 json으로 받음
        return new ApiUtil<>(boardList);
    }
}
