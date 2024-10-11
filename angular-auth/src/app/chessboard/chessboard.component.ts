import {Component, AfterViewInit, ElementRef, ViewChild, Inject, PLATFORM_ID} from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import {Chess, Square} from 'chess.js';
declare var Chessboard: any;

@Component({
  selector: 'app-chessboard',
  standalone: true,
  imports: [],
  templateUrl: './chessboard.component.html',
  styleUrl: './chessboard.component.css'
})
export class ChessboardComponent implements AfterViewInit {
  @ViewChild('board', { static: false }) boardElement!: ElementRef;
  private board: any;
  private game: Chess;

  constructor(@Inject(PLATFORM_ID) private platformId: object) {
    this.game = new Chess();
  }

  ngAfterViewInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.loadScript().then(() => {
        this.board = Chessboard(this.boardElement.nativeElement, {
          draggable: true,
          position: 'start',
          pieceTheme: 'assets/img/chesspieces/wikipedia/{piece}.png',
          onDrop: this.onDrop.bind(this),
          moveSpeed: 0,
          showNotation: false
        });
      }).catch(error => {
        console.error('Error loading chessboard.js:', error);
      });
    }
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
    // Проверяем, находится ли целевая клетка в пределах доски
    const isValidTarget = /^[a-h][1-8]$/.test(target);

    if (!isValidTarget) {
      setTimeout(() => this.board.position(this.game.fen()), 0);
      return 'snapback';
    }

    const possibleMoves = this.game.moves({  verbose: true, square: source as Square });
    const isMoveLegal = possibleMoves.some((move) => move.to === target);

    if (!isMoveLegal) {
      setTimeout(() => this.board.position(this.game.fen()), 0); // Возвращаем фигуру
      return 'snapback';
    }

    // Выполняем ход
    const move = this.game.move({
      from: source,
      to: target,
      promotion: 'q'
    });

    if (move === null) {
      setTimeout(() => this.board.position(this.game.fen()), 0); // Возвращаем фигуру
      return 'snapback';
    } else {
      console.log(`Moved ${source} to ${target}`);
    }

    this.updateBoard();

    // Если ход некорректен, возвращаем фигуру на место
    if (move === null) {
      setTimeout(() => this.board.position(this.game.fen()), 0);
      return 'snapback';
    } else {
      console.log(`Moved ${source} to ${target}`);
    }

    // Обновляем позицию доски после успешного хода
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
