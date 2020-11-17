import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {Authority} from './authority'
import {Resource} from './resource'

@Entity("t_resource_authority",'tunnel')
export class ResourceAuthority extends BaseEntity{
	@Id()
	@Column({
		name:'resource_authority_id',
		type:'int',
		nullable:false
	})
	private resourceAuthorityId:number;

	@ManyToOne({entity:'Authority',eager:false})
	@JoinColumn({name:'authority',refName:'authority'})
	private authority:Authority;

	@ManyToOne({entity:'Resource',eager:false})
	@JoinColumn({name:'resource_id',refName:'resource_id'})
	private resource:Resource;

	public getResourceAuthorityId():number{
		return this.resourceAuthorityId;
	}
	public setResourceAuthorityId(value:number){
		this.resourceAuthorityId = value;
	}

	public getAuthority():Authority{
		return this.authority;
	}
	public setAuthority(value:Authority){
		this.authority = value;
	}

	public getResource():Resource{
		return this.resource;
	}
	public setResource(value:Resource){
		this.resource = value;
	}

}