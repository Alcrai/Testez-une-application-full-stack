import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { SessionsRoutingModule } from './sessions-routing.module';
import { ListComponent } from './components/list/list.component';
import { DetailComponent } from './components/detail/detail.component';
import { FormComponent } from './components/form/form.component';
import { Router } from '@angular/router';
import { expect } from '@jest/globals';

describe('SessionsRoutingModule', () => {
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([]), SessionsRoutingModule],
      declarations: [ListComponent, DetailComponent, FormComponent]
    }).compileComponents();

    router = TestBed.inject(Router);
  });

  it('should navigate to sessions list', () => {
    const navigateSpy = jest.spyOn(router, 'navigate');

    router.navigate(['/']);

    expect(navigateSpy).toHaveBeenCalledWith(['/']);
  });

  it('should navigate to session detail', () => {
    const navigateSpy = jest.spyOn(router, 'navigate');

    router.navigate(['/detail/1']);

    expect(navigateSpy).toHaveBeenCalledWith(['/detail/1']);
  });

  it('should navigate to session create', () => {
    const navigateSpy = jest.spyOn(router, 'navigate');

    router.navigate(['/create']);

    expect(navigateSpy).toHaveBeenCalledWith(['/create']);
  });

  it('should navigate to session update', () => {
    const navigateSpy = jest.spyOn(router, 'navigate');

    router.navigate(['/update/1']);

    expect(navigateSpy).toHaveBeenCalledWith(['/update/1']);
  });
});
