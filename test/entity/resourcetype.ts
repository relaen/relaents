import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {Resource} from './resource'

@Entity("t_resource_type",'tunnel')
export class ResourceType extends BaseEntity{
	@Id()
	@Column({
		name:'resource_type_id',
		type:'int',
		nullable:false
	})
	private resourceTypeId:number;

	@Column({
		name:'type_name',
		type:'string',
		nullable:true
	})
	private typeName:string;

	@OneToMany({entity:'Resource',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'resourceType',eager:false})
	private resources:Array<Resource>;

	public getResourceTypeId():number{
		return this.resourceTypeId;
	}
	public setResourceTypeId(value:number){
		this.resourceTypeId = value;
	}

	public getTypeName():string{
		return this.typeName;
	}
	public setTypeName(value:string){
		this.typeName = value;
	}

	public getResources():Array<Resource>{
		return this.resources;
	}
	public setResources(value:Array<Resource>){
		this.resources = value;
	}

}