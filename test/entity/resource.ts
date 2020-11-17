import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {ResourceType} from './resourcetype'
import {ResourceAuthority} from './resourceauthority'

@Entity("t_resource",'tunnel')
export class Resource extends BaseEntity{
	@Id()
	@Column({
		name:'resource_id',
		type:'int',
		nullable:false
	})
	private resourceId:number;

	@ManyToOne({entity:'ResourceType',eager:false})
	@JoinColumn({name:'resource_type_id',refName:'resource_type_id'})
	private resourceType:ResourceType;

	@Column({
		name:'url',
		type:'string',
		nullable:true
	})
	private url:string;

	@Column({
		name:'title',
		type:'string',
		nullable:true
	})
	private title:string;

	@OneToMany({entity:'ResourceAuthority',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'resource',eager:false})
	private resourceAuthoritys:Array<ResourceAuthority>;

	public getResourceId():number{
		return this.resourceId;
	}
	public setResourceId(value:number){
		this.resourceId = value;
	}

	public getResourceType():ResourceType{
		return this.resourceType;
	}
	public setResourceType(value:ResourceType){
		this.resourceType = value;
	}

	public getUrl():string{
		return this.url;
	}
	public setUrl(value:string){
		this.url = value;
	}

	public getTitle():string{
		return this.title;
	}
	public setTitle(value:string){
		this.title = value;
	}

	public getResourceAuthoritys():Array<ResourceAuthority>{
		return this.resourceAuthoritys;
	}
	public setResourceAuthoritys(value:Array<ResourceAuthority>){
		this.resourceAuthoritys = value;
	}

}