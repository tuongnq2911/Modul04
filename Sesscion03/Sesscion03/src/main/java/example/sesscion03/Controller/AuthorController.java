package example.sesscion03.Controller;

import example.sesscion03.DTO.AuthorUpdateRequest;
import example.sesscion03.Model.Author;
import example.sesscion03.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    // Bài tập 1
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }
    // Bài tập 2
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    // Bài tập 3
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            return ResponseEntity.ok(author);
        }else {
            return ResponseEntity.status(404).body("Không tìm thấy tác giả có ID: "+ id);
        }
    }

    // Bài tập 4
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable int id, @RequestBody AuthorUpdateRequest request) {
        Author updatedAuthor = authorService.updateAuthor(id, request);
        if (updatedAuthor != null) {
            return ResponseEntity.ok(updatedAuthor);
        }else {
            return ResponseEntity.status(404).body("Không tìm thấy tác giả có ID: " + id);
        }
    }

    // Bài tập 5
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id) {
        Optional<Author> optionalAuthor = authorService.getAuthorById(id) == null ?
                Optional.empty() : Optional.of(authorService.getAuthorById(id));
        if (optionalAuthor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tác giả");
        }
        Author author = optionalAuthor.get();
        if (author.getName().equalsIgnoreCase("Admin")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không thể xoá tác giả có vai trò Administrator");
        }
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Xoá thành công");
    }

    // Bài tập 6
    @GetMapping("/search")
    public  List<Author> searchAuthors(@RequestParam("name") String keyword) {
        return authorService.searchAuthors(keyword);
    }
}
