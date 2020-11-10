
import {Area} from './area'
import {DeviceModel} from './devicemodel'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_provider",'tunnel')
export class Provider extends BaseEntity{
	@Id()
	@Column({
		name:'provider_id',
		type:'int',
		nullable:false
	})
	providerId:number;

	@ManyToOne({entity:Provider,lazyFetch:true})
	@JoinColumn({name:'area_id',refName:'area_id'})
	area:Area;

	@Column({
		name:'provider_name',
		type:'string',
		nullable:true
	})
	providerName:string;

	@Column({
		name:'contact_man',
		type:'string',
		nullable:true
	})
	contactMan:string;

	@Column({
		name:'manager',
		type:'string',
		nullable:true
	})
	manager:string;

	@Column({
		name:'manager_idno',
		type:'string',
		nullable:true
	})
	managerIdno:string;

	@Column({
		name:'tel',
		type:'string',
		nullable:true
	})
	tel:string;

	@Column({
		name:'mobile',
		type:'string',
		nullable:true
	})
	mobile:string;

	@Column({
		name:'address',
		type:'string',
		nullable:true
	})
	address:string;

	@Column({
		name:'zipcode',
		type:'string',
		nullable:true
	})
	zipcode:string;

	@Column({
		name:'nation',
		type:'string',
		nullable:true
	})
	nation:string;

	@OneToMany({entity:'DeviceModel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'provider',lazyFetch:true})
	deviceModels:Array<DeviceModel>;

}