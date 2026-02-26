package example.sesscion03.Service;

import example.sesscion03.DTO.AuthorUpdateRequest;
import example.sesscion03.Model.Author;
import example.sesscion03.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    // Bài tập 1
    @Autowired
    private AuthorRepository authorRepository;
    public ArrayList<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    // Bài tập 2
    public Author createAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    // Bài tập 3
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    // Bài tập 4
    public Author updateAuthor(int id, AuthorUpdateRequest request) {
        return authorRepository.findById(id).
                map(existingAuthor -> {
                    existingAuthor.setName(request.getName());
                    existingAuthor.setEmail(request.getEmail());
                    return existingAuthor;
                }).orElse(null);
    }

    // Bài tâp 5
    public boolean deleteAuthor(int id) {
        //B1 Tìm tác giả theo ID
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        // Nếu không tìm thấy
        if (optionalAuthor.isEmpty()) {
            return false;
        }
        Author author = optionalAuthor.get();

        // B2: Validate không cho xoá Admin ( Không phân biệt hoa thường)
        if (author.getName().equalsIgnoreCase("Admin")){
            return false;
        }
        authorRepository.deleteById(id);
        return true;
    }

    // Bài tập 6
    public List<Author> searchAuthors(String keyword) {
        List<Author> result = new ArrayList<>();

        // Nếu keywork null hoặc rỗng -> trả về toàn bộ tác giả
        if (keyword == null || keyword.isEmpty()) {
            return authorRepository.findAll();
        }

        // Chuyển keyword về lowercase để so sánh
        String lowerCaseKeyword = keyword.toLowerCase();

        for (Author author : authorRepository.findAll()) {
            if (author.getName().toLowerCase().contains(lowerCaseKeyword)) {
                result.add(author);
            }
        }
        return result;
    }
}
