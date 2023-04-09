import java.util.Arrays;

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
        return index > -1 ? storage[index] : null;
    }

    void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index > -1) {
            int countResumes = sizeStorage - index - 1;
            System.arraycopy(storage, index + 1, storage, index, countResumes);
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

    private int findResumeIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
