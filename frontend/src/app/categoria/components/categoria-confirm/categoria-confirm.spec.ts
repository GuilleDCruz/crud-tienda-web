import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoriaConfirm } from './categoria-confirm';

describe('CategoriaConfirm', () => {
  let component: CategoriaConfirm;
  let fixture: ComponentFixture<CategoriaConfirm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoriaConfirm],
    }).compileComponents();

    fixture = TestBed.createComponent(CategoriaConfirm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
