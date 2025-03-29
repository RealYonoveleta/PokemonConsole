package com.yonoveleta.pokemon.turn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yonoveleta.pokemon.move.Move;
import com.yonoveleta.pokemon.pokemon.Pokemon;
import com.yonoveleta.pokemon.trainer.Trainer;

public class TurnManagerTest {

    @Mock
    private Trainer mockTrainer;
    
    @Mock
    private Pokemon mockUser;
    
    @Mock
    private Pokemon mockTarget;
    
    @Mock
    private Move mockMove;
    
    @Mock
    private TurnAction mockTurnAction;
    
    @InjectMocks
    private TurnManager turnManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testAddAction() {
        // Given
        Trainer trainer = mock(Trainer.class);
        Pokemon user = mock(Pokemon.class);
        Move move = mock(Move.class);
        Pokemon target = mock(Pokemon.class);
        Trainer rival = mock(Trainer.class);

        // When
        turnManager.addAction(trainer, user, move, target, rival);
        
        // Then
        Queue<TurnAction> actionQueue = turnManager.getActionQueue();  // Assuming you have a getter for actionQueue
        assertEquals(1, actionQueue.size(), "Action should be added to the queue");
    }

    @Test
    void testProcessTurn_WhenActionIsPerformed() {
        when(mockTurnAction.getUser()).thenReturn(mockUser);
        when(mockTurnAction.getTarget()).thenReturn(mockTarget);
        doNothing().when(mockTurnAction).run();  // Mock the run method to do nothing

        turnManager.addAction(mockTrainer, mockUser, mockMove, mockTarget, mockTrainer);
        assertEquals(turnManager.getActionQueue().size(), 1);
        
        turnManager.processTurn();

        assertEquals(turnManager.getActionQueue().size(), 0);
    }

    @Test
    public void testProcessTurn_WhenPokemonFaints() {
        // Given
        when(mockTurnAction.getUser()).thenReturn(mockUser);
        when(mockUser.getHp()).thenReturn(0);  // Pokemon is fainted
        when(mockUser.canMove()).thenReturn(true);
        when(mockTurnAction.getRival()).thenReturn(mockTrainer);
        when(mockTurnAction.getTarget()).thenReturn(mockTarget);

        turnManager.addAction(mockTrainer, mockUser, mockMove, mockTarget, mockTrainer);

        // When
        turnManager.processTurn();

        // Then
        verify(mockTurnAction, never()).run();  // Action should not be executed if the Pokemon is fainted
    }
}
