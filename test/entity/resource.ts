
import {ResourceType} from './resourcetype'
import {ResourceAuthority} from './resourceauthority'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_resource",'tunnel')
export class Resource extends BaseEntity{
	@Id()
	@Column({
		name:'resource_id',
		type:'int',
		nullable:false
	})
	resourceId:number;

	@ManyToOne({entity:Resource,lazyFetch:true})
	@JoinColumn({name:'resource_type_id',refName:'resource_type_id'})
	resourceType:ResourceType;

	@Column({
		name:'url',
		type:'string',
		nullable:true
	})
	url:string;

	@Column({
		name:'title',
		type:'string',
		nullable:true
	})
	title:string;

	@OneToMany({entity:'ResourceAuthority',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'resource',lazyFetch:true})
	resourceAuthoritys:Array<ResourceAuthority>;

}