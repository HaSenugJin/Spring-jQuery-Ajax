package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiUtil<T> {
    private Integer status; // 200, 400, 404, 405
    private String msg; // 성공, 실패시 -> 실패 이유 정확하게
    private T body;

    // 성공시 바디 데이터만 받아서 보내고 나머지는 디폴트로 200, 성공으로 둔다
    public ApiUtil(T body) {
        this.status = 200;
        this.msg = "성공";
        this.body = body;
    }

    // 실패시 바디 데이터는 없으니 null
    public ApiUtil(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.body = null;
    }
}
