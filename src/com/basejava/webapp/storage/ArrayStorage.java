package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int sizeStorage;

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public void save(Resume r) {
        int index = findResumeIndex(r.getUuid());
        boolean isFullStorage = sizeStorage >= storage.length;
        if (index == -1 && !isFullStorage) {
            storage[sizeStorage] = r;
            sizeStorage++;
        } else if (isFullStorage) {
            System.out.println("Массив резюме полон");
        } else {
            System.out.println("Резюме " + r.getUuid() + " уже есть.");
        }
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index > -1) {
            storage[index] = r;
        } else {
            System.out.println("Резюме " + r.getUuid() + " не найдено.");
        }
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        System.out.println("Резюме " + uuid + " не обнаружено.");
        return null;
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index > -1) {
            int countResumes = sizeStorage - index - 1;
            System.arraycopy(storage, index + 1, storage, index, countResumes);
            sizeStorage--;
        } else {
            System.out.println("Резюме " + uuid + " не обнаружено.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    public int size() {
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
