import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {GroupAuthority} from './groupauthority'
import {ResourceAuthority} from './resourceauthority'

@Entity("t_authority",'tunnel')
export class Authority extends BaseEntity{
	@Id()
	@Column({
		name:'authority',
		type:'string',
		nullable:false
	})
	private authority:string;

	@OneToMany({entity:'GroupAuthority',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'authority',eager:false})
	private groupAuthoritys:Array<GroupAuthority>;

	@OneToMany({entity:'ResourceAuthority',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'authority',eager:false})
	private resourceAuthoritys:Array<ResourceAuthority>;

	public getAuthority():string{
		return this.authority;
	}
	public setAuthority(value:string){
		this.authority = value;
	}

	public getGroupAuthoritys():Array<GroupAuthority>{
		return this.groupAuthoritys;
	}
	public setGroupAuthoritys(value:Array<GroupAuthority>){
		this.groupAuthoritys = value;
	}

	public getResourceAuthoritys():Array<ResourceAuthority>{
		return this.resourceAuthoritys;
	}
	public setResourceAuthoritys(value:Array<ResourceAuthority>){
		this.resourceAuthoritys = value;
	}

}