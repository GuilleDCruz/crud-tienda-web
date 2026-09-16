import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoProductoList } from './tipo-producto-list';

describe('TipoProductoList', () => {
  let component: TipoProductoList;
  let fixture: ComponentFixture<TipoProductoList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TipoProductoList],
    }).compileComponents();

    fixture = TestBed.createComponent(TipoProductoList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
