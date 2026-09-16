import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoProductoConfirm } from './tipo-producto-confirm';

describe('TipoProductoConfirm', () => {
  let component: TipoProductoConfirm;
  let fixture: ComponentFixture<TipoProductoConfirm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TipoProductoConfirm],
    }).compileComponents();

    fixture = TestBed.createComponent(TipoProductoConfirm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
