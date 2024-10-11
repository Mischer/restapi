package com.mk.restapi.service;

import com.mk.restapi.service.impl.GameServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.mk.restapi.repository.MoveRepository;
import com.mk.restapi.repository.GameRepository;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private MoveRepository moveRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    public void testProcessMove() {
        // TODO implement
    }
}
