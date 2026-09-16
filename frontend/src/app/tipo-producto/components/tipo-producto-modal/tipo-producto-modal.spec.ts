import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoProductoModal } from './tipo-producto-modal';

describe('TipoProductoModal', () => {
  let component: TipoProductoModal;
  let fixture: ComponentFixture<TipoProductoModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TipoProductoModal],
    }).compileComponents();

    fixture = TestBed.createComponent(TipoProductoModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
