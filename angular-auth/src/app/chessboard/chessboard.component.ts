import {Component, AfterViewInit, ElementRef, ViewChild, Inject, PLATFORM_ID} from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import { GameService } from './game.service';
import {Chess, Square} from 'chess.js';
import {FormsModule} from "@angular/forms";
declare var Chessboard: any;

@Component({
  selector: 'app-chessboard',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './chessboard.component.html',
  styleUrl: './chessboard.component.css'
})
export class ChessboardComponent implements AfterViewInit {
  @ViewChild('board', { static: false }) boardElement!: ElementRef;
  private board: any;
  private game: Chess;

  whitePlayerId: number | null = null;
  blackPlayerId: number | null = null;

  constructor(
    @Inject(PLATFORM_ID) private platformId: object,
    private gameService: GameService
  ) {
    this.game = new Chess();
  }

  ngAfterViewInit(): void {
    // moved to createGame()
  }

  createGame(): void {
    if (this.whitePlayerId == null || this.blackPlayerId == null) {
      alert('Fill the player ids');
      return;
    }

    const requestBody = {
      whitePlayerId: this.whitePlayerId,
      blackPlayerId: this.blackPlayerId
    };

    this.gameService.createGame(requestBody).subscribe(
      (response) => {
        console.log('Game is created:', response);
        this.game.reset();
        this.game.load(response.currentFen);

        // Init the board
        if (isPlatformBrowser(this.platformId)) {
          this.loadScript().then(() => {
            this.board = Chessboard(this.boardElement.nativeElement, {
              draggable: true,
              position: response.currentFen,
              pieceTheme: 'assets/img/chesspieces/wikipedia/{piece}.png',
              onDrop: this.onDrop.bind(this),
              moveSpeed: 0,
              showNotation: false
            });
          }).catch(error => {
            console.error('Error loading chessboard.js:', error);
          });
        }
      },
      (error) => {
        console.error('Error creating game:', error);
        alert('Error creating game: ' + error.error.message);
      }
    );
  }

  private loadScript(): Promise<void> {
    return new Promise((resolve, reject) => {
      const scriptElement = document.createElement('script');
      scriptElement.src = 'assets/chessboardjs/chessboard-1.0.0.js';
      scriptElement.onload = () => {
        resolve();
      };
      scriptElement.onerror = () => {
        reject(new Error('Failed to load chessboard.js'));
      };
      document.body.appendChild(scriptElement);
    });
  }

  onDrop(source: string, target: string): any {
    // Check the square in the board area
    const isValidTarget = /^[a-h][1-8]$/.test(target);

    if (!isValidTarget) {
      setTimeout(() => this.board.position(this.game.fen()), 0);
      return 'snapback';
    }

    const possibleMoves = this.game.moves({  verbose: true, square: source as Square });
    const isMoveLegal = possibleMoves.some((move) => move.to === target);

    if (!isMoveLegal) {
      setTimeout(() => this.board.position(this.game.fen()), 0); // Take one move back
      return 'snapback';
    }

    // Make a move
    const move = this.game.move({
      from: source,
      to: target,
      promotion: 'q'
    });

    if (move === null) {
      setTimeout(() => this.board.position(this.game.fen()), 0); // Take one move back
      return 'snapback';
    } else {
      console.log(`Moved ${source} to ${target}`);
    }

    this.updateBoard();
  }

  updateBoard(): void {
    setTimeout(() => this.board.position(this.game.fen()), 0);
  }

  resetBoard(): void {
    this.game.reset();
    this.board.start();
  }

  clearBoard(): void {
    this.game.clear();
    this.board.clear();
  }
}
