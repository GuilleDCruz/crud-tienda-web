import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarcaConfirm } from './marca-confirm';

describe('MarcaConfirm', () => {
  let component: MarcaConfirm;
  let fixture: ComponentFixture<MarcaConfirm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MarcaConfirm],
    }).compileComponents();

    fixture = TestBed.createComponent(MarcaConfirm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
