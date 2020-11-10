
import {Resource} from './resource'
import { Entity, Id, Column, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_resource_type",'tunnel')
export class ResourceType extends BaseEntity{
	@Id()
	@Column({
		name:'resource_type_id',
		type:'int',
		nullable:false
	})
	resourceTypeId:number;

	@Column({
		name:'type_name',
		type:'string',
		nullable:true
	})
	typeName:string;

	@OneToMany({entity:'Resource',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'resourceType',lazyFetch:true})
	resources:Array<Resource>;

}