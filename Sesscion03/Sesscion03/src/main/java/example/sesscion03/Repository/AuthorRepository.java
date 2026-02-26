package example.sesscion03.Repository;

import example.sesscion03.Model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class AuthorRepository {
    private final ArrayList<Author> authors = new ArrayList<>();
    // Bài tập 1
    public AuthorRepository() {
        authors.add(new Author(1, "Nguyễn Nhật Ánh", "nna@gmail.com"));
        authors.add(new Author(2,"Nam Cao","namcao@gmail.com"));
        authors.add(new Author(3,"Tô Hoài","tohoa@gmail.com"));
    }
    public ArrayList<Author> findAll() {
        return authors;
    }
    // Bài tập 2
    public void save(Author author) {
        authors.add(author);
    }

    // Bài tập 3
    public Optional<Author> findById(int id) {
        return authors.stream().filter(a -> a.getId() == id).findFirst();
    }

    // Bài tập 5
    public void deleteById(int id) {
        authors.removeIf(a -> a.getId() == id);
    }


}
