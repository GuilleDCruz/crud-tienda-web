import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductoConfirm } from './producto-confirm';

describe('ProductoConfirm', () => {
  let component: ProductoConfirm;
  let fixture: ComponentFixture<ProductoConfirm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductoConfirm],
    }).compileComponents();

    fixture = TestBed.createComponent(ProductoConfirm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
