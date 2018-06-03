import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatButtonModule,
  MatCardModule,
  MatDialogModule,
  MatInputModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';

@NgModule({
  imports: [
    MatButtonModule,
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatInputModule,
    MatToolbarModule,
    MatTableModule
  ],
  exports: [
    MatButtonModule,
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatInputModule,
    MatToolbarModule,
    MatTableModule
  ]
})
export class MaterialModule {}
