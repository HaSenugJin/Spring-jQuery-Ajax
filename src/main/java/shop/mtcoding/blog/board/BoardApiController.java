package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    // 자바 스크립트 요청은 페이지를 하면 안되고 JSON을 해야함


    private final  BoardRepository boardRepository;

    @PutMapping("/api/boards/{id}")
    public ApiUtil<?> update(HttpServletRequest request, @PathVariable Integer id, @RequestBody BoardRequest.UpdateDTO boardRequest) {
        boardRepository.update(boardRequest, id);
        request.setAttribute("id", id);
        System.out.println(boardRequest);
        return new ApiUtil<>(null);
    }

    @PostMapping("/api/boards")
    // @RequestBody = JSON데이터를 받을 수 있음
    public ApiUtil<?> write(@RequestBody BoardRequest.WriteDTO boardRequest) {

        boardRepository.insert(boardRequest);

        return new ApiUtil<>(null);
    }

    @DeleteMapping("/api/boards/{id}")
    public ApiUtil<?> deleteById(@PathVariable Integer id, HttpServletResponse response) {
        Board board = boardRepository.selectOne(id);
        if (board == null) {
            response.setStatus(404);
            return new ApiUtil<>(404, "해당 게시글을 찾을 수 없습니다.");
        }

        boardRepository.deleteById(id);
        return new ApiUtil<>(null); // 삭제는 데이터를 줄게 없으니 null
    }


    @GetMapping("/api/boards")
    public ApiUtil<List<Board>> findAll(HttpServletResponse response) {
        // response.setStatus(200);

        // 오브젝트를 리턴하면 스프링이 자동으로 json으로 받아줌
        List<Board> boardList = boardRepository.selectAll();

        // 메시지 컨버터라는 메서드가 오브젝트를 리턴할 때 자동으로 발동되어서 json으로 받음
        return new ApiUtil<>(boardList);
    }
}
