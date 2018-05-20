import { TemplateRole } from './template-role';

export class TemplateUser {
  constructor(public id: string, public roles: TemplateRole[]) {}
}
