package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Массив резюме полон");
        } else if (index != -1) {
            System.out.println("Резюме " + resume.getUuid() + " уже есть.");
        } else {
            saveResume(resume, index);
            size++;
        }
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Резюме " + resume.getUuid() + " не найдено.");
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
            deleteResume(index);
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

    protected abstract int findIndex(String uuid);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);
}
