
import {Agent} from './agent'
import {Provider} from './provider'
import {Road} from './road'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_area",'tunnel')
export class Area extends BaseEntity{
	@Id()
	@Column({
		name:'area_id',
		type:'int',
		nullable:false
	})
	areaId:number;

	@ManyToOne({entity:Area,lazyFetch:true})
	@JoinColumn({name:'parent_id',refName:'parent_id'})
	area:Area;

	@Column({
		name:'area_name',
		type:'string',
		nullable:true
	})
	areaName:string;

	@Column({
		name:'area_code',
		type:'string',
		nullable:true
	})
	areaCode:string;

	@Column({
		name:'zip_code',
		type:'string',
		nullable:true
	})
	zipCode:string;

	@Column({
		name:'depth',
		type:'int',
		nullable:true
	})
	depth:number;

	@OneToMany({entity:'Agent',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'area',lazyFetch:true})
	agents:Array<Agent>;

	@OneToMany({entity:'Area',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'area',lazyFetch:true})
	areas:Array<Area>;

	@OneToMany({entity:'Provider',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'area',lazyFetch:true})
	providers:Array<Provider>;

	@OneToMany({entity:'Road',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'area',lazyFetch:true})
	roads:Array<Road>;

}