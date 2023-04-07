import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int sizeStorage;

    void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    void save(Resume r) {
        storage[sizeStorage] = r;
        sizeStorage++;
    }

    Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    private int findResumeIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            List<Resume> tempStorage = new ArrayList<>(Arrays.asList(storage));
            tempStorage.remove(index);
            storage = tempStorage.toArray(new Resume[0]);
            sizeStorage--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    int size() {
        return sizeStorage;
    }
}
