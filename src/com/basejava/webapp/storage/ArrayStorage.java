package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        boolean isFullStorage = size >= STORAGE_LIMIT;
        if (isFullStorage) {
            System.out.println("Массив резюме полон");
        } else if (index != -1) {
            System.out.println("Резюме " + r.getUuid() + " уже есть.");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index > -1) {
            storage[index] = r;
        } else {
            System.out.println("Резюме " + r.getUuid() + " не найдено.");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        System.out.println("Резюме " + uuid + " не обнаружено.");
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index > -1) {
            storage[index] = storage[size - 1];
            size--;
        } else {
            System.out.println("Резюме " + uuid + " не обнаружено.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
