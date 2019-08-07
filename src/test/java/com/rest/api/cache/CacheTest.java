package com.rest.api.cache;

import com.rest.api.entity.board.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {

    @Autowired
    private CacheRepo cacheRepo;

    @Test
    public void cacheTest() throws Exception {
        // get cache
        Post post = cacheRepo.getPost(1L);
        assertSame(1L, post.getPostId());
        assertEquals("title_1", post.getTitle());
        // update cache
        post.setTitle("title_modified");
        post.setContent("content_modified");
        cacheRepo.updatePost(post);
        // get cache
        Post postModified = cacheRepo.getPost(1L);
        assertEquals("title_modified", postModified.getTitle());
        assertEquals("content_modified", postModified.getContent());
    }

    @Test
    public void cacheTestMultiKey() throws Exception {
        // get cache
        Post post = cacheRepo.getPostMultiKey(1L, "title_1");
        assertSame(1L, post.getPostId());
        assertEquals("title_1", post.getTitle());
        // update cache
        post.setTitle("title_modified");
        post.setContent("content_modified");
        cacheRepo.updatePostMultiKey(post);
        // get cache
        Post postModified = cacheRepo.getPostMultiKey(1L, "title_modified");
        assertEquals("title_modified", postModified.getTitle());
        assertEquals("content_modified", postModified.getContent());
    }

    @Test
    public void cacheTestCustomKeyGenerator() throws Exception {
        // get cache
        Post post = cacheRepo.getPostKeyGenerator(1L, "title_1");
        assertSame(1L, post.getPostId());
        assertEquals("title_1", post.getTitle());
    }

    @Test
    public void deleteAllCache() {
        cacheRepo.getPost(1L);
        cacheRepo.getPost(2L);
        cacheRepo.getPost(3L);
        cacheRepo.getPost(4L);
        cacheRepo.clearCache();
    }
}